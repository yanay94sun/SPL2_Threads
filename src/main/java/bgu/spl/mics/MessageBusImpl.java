package bgu.spl.mics;

import java.util.Map;
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
	private ConcurrentHashMap<Event,Future> futures; // /\/\ meybe we need to change here all to Map and not ConcurrentHashMap
	private ConcurrentHashMap<MicroService, BlockingDeque<Message>> microServices; // /\/\ meybe here just a regular Q
	private ConcurrentHashMap<Class<? extends Message>, ConcurrentLinkedQueue<MicroService>> messages;

	public MessageBusImpl(){
		futures= new ConcurrentHashMap<>();
		microServices = new ConcurrentHashMap<>();
		messages = new ConcurrentHashMap<>();
	}

	public static MessageBusImpl getInstance() {
		return messageBus;
	} // /\/\ read online that should be getInstance in singelton
	
	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		synchronized (type) {
			if (!messages.containsKey(type)) {
				ConcurrentLinkedQueue a = new ConcurrentLinkedQueue<MicroService>();
				messages.put(type, a);
			}
			messages.get(type).add(m);
		}
		
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		
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
		
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		
		return null;
	}
}
