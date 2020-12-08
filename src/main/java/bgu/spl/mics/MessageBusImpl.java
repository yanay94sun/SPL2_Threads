package bgu.spl.mics;

import java.util.Iterator; // ??? possible right?
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements MessageBus {
	private static MessageBusImpl messageBus = new MessageBusImpl(); // read online that should be getInstance in singelton /\/\
	private Map<Event,Future> futures; // /\/\ meybe we need to change here all to Map and not ConcurrentHashMap
	private ConcurrentHashMap<MicroService, BlockingDeque<Message>> microServices; // /\/\ meybe here just a regular Q
	private ConcurrentHashMap<Class<? extends Message>, ConcurrentLinkedQueue<MicroService>> messages;

	public MessageBusImpl(){
		futures= new ConcurrentHashMap<>(); // hashMap of futures that the key is the event and the value is the futures resolve
		microServices = new ConcurrentHashMap<>(); // hashMap of microServices that the key is the microServices and the value is the his message queue
		messages = new ConcurrentHashMap<>();// for the round robin // hashMap of messages that the key is the ???messages??? and the value is the Q of microServices subscribe to it
	}

	public static MessageBusImpl getInstance() {
		return messageBus;
	} // /\/\ read online that should be getInstance in singelton
	
	// if the type of the event already in the message's Map - we add the MicroService to the microServiceQ related to it
	// if not - we create a new microServiceQ related to this type of event
	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		synchronized (type) {
			if (!messages.containsKey(type)) {
				ConcurrentLinkedQueue microServiceQ = new ConcurrentLinkedQueue<MicroService>();
				messages.put(type, microServiceQ);
			}
			messages.get(type).add(m);
		}
		
	}

	// same as subscribe event
	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		synchronized (type) {
			if (!messages.containsKey(type)) {
				ConcurrentLinkedQueue microServiceQ = new ConcurrentLinkedQueue<MicroService>();
				messages.put(type, microServiceQ);
			}
			messages.get(type).add(m);
		}
		
    }

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		futures.get(e).resolve(result); // /\/\
	}
	//sends broadcast to *all* the e Microservices that are interested in it.
	@Override
	public void sendBroadcast(Broadcast b) {
		synchronized (b.getClass()){
//			messages.get(b.getClass()).forEach();
//			Set<MicroService> microServiceSet = microServices.keySet();

			//			for (MicroService microService : messages.get(b.getClass()))
			for (MicroService microService : messages.get(b.getClass())) {
				if (microServices.containsKey(microService))
					microServices.get(microService.getClass()).add(b);
			}
		}
	}

	//send event to *one* of the Microservices that are interested in it. (round robin)
	@Override
	public <T> Future<T> sendEvent(Event<T> e) { // not full anderstand this method implement by /\/\
		if (messages.containsKey(e.getClass())) {
			Future<T> future = new Future<>();
			futures.put(e, future);
			if (messages.get(e.getClass()) != null) { // check if the microservice Q is empty
				synchronized (e.getClass()) {
					MicroService m = messages.get(e.getClass()).poll();// remove and returns the head for the round rubin
					if (m != null) { // check if the microservice is alive
						microServices.get(m).add(e);
						messages.get(e.getClass()).add(m);
					}
				}
				return future;
			}
		}
		return null;
	}

	@Override
	public void register(MicroService m) {
		microServices.putIfAbsent(m, new LinkedBlockingDeque<>()); // /\/\ meybe just blocking dequeue

	}

	@Override
	public void unregister(MicroService m) {
		synchronized (m) {
			Set<Class<? extends Message>> removeMessageSet = messages.keySet();
			for (Class message : removeMessageSet) { // removing all the microservices related to the message from messages
				messages.get(message).remove(m);
			}
			Set<Event> eventSet = futures.keySet();
			for (Event event : eventSet) { // resolving to null all the futures related to the event of the microservice
				if (microServices.get(m).contains(futures.get(event))) {
					futures.get(event).resolve(null);
				}
			}
		}
		microServices.remove(m);
		
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		return microServices.get(m).take(); // taking some event from the Q
	}
}
