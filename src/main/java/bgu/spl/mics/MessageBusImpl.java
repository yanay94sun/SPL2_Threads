package bgu.spl.mics;

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
	private final static MessageBusImpl messageBus = new MessageBusImpl(); // read online that should be getInstance in singelton /\/\
	private ConcurrentHashMap<Event,Future> futures; // /\/\ meybe we need to change here all to Map and not ConcurrentHashMap
	private ConcurrentHashMap<MicroService, BlockingDeque<Message>> microServices; // /\/\ meybe here just a regular Q
	private ConcurrentHashMap<Class<? extends Message>, ConcurrentLinkedQueue<MicroService>> messages;

	private MessageBusImpl(){
		futures= new ConcurrentHashMap<>(); // hashMap of futures that the key is the event and the value is the futures resolve
		microServices = new ConcurrentHashMap<>(); // hashMap of microServices that the key is the microServices and the value is the his message queue
		messages = new ConcurrentHashMap<>(); // hashMap of messages that the key is the ???messages??? and the value is the microServices subscribe to it
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

	@Override
	public void sendBroadcast(Broadcast b) {
		
	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
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
