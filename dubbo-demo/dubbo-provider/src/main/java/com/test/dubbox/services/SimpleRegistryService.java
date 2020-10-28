package com.test.dubbox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.RegistryService;

@Service("simpleRegistryService")
public class SimpleRegistryService implements RegistryService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleRegistryService.class);

	private final Set<URL> registered = new ConcurrentHashSet<URL>();
	private final ConcurrentMap<URL, Set<NotifyListener>> subscribed = new ConcurrentHashMap<URL, Set<NotifyListener>>();
	private final ConcurrentMap<URL, Map<String, List<URL>>> notified = new ConcurrentHashMap<URL, Map<String, List<URL>>>();

	public SimpleRegistryService() {
	}

	public void register(URL url) {
		if (url == null) {
			throw new IllegalArgumentException("register url == null");
		}
		if (logger.isInfoEnabled()) {
			logger.info("Register: " + url);
		}
		registered.add(url);
	}

	public void unregister(URL url) {
		if (url == null) {
			throw new IllegalArgumentException("unregister url == null");
		}
		if (logger.isInfoEnabled()) {
			logger.info("Unregister: " + url);
		}
		registered.remove(url);
	}

	public void subscribe(URL url, NotifyListener listener) {
		if (url == null) {
			throw new IllegalArgumentException("subscribe url == null");
		}
		if (listener == null) {
			throw new IllegalArgumentException("subscribe listener == null");
		}
		if (logger.isInfoEnabled()) {
			logger.info("Subscribe: " + url);
		}
		Set<NotifyListener> listeners = subscribed.get(url);
		if (listeners == null) {
			subscribed.putIfAbsent(url, new ConcurrentHashSet<NotifyListener>());
			listeners = subscribed.get(url);
		}
		listeners.add(listener);
	}

	public void unsubscribe(URL url, NotifyListener listener) {
		if (url == null) {
			throw new IllegalArgumentException("unsubscribe url == null");
		}
		if (listener == null) {
			throw new IllegalArgumentException("unsubscribe listener == null");
		}
		if (logger.isInfoEnabled()) {
			logger.info("Unsubscribe: " + url);
		}
		Set<NotifyListener> listeners = subscribed.get(url);
		if (listeners != null) {
			listeners.remove(listener);
		}
	}

	public List<URL> lookup(URL url) {
		List<URL> result = new ArrayList<URL>();
		Map<String, List<URL>> notifiedUrls = getNotified().get(url);
		if (notifiedUrls != null && notifiedUrls.size() > 0) {
			for (List<URL> urls : notifiedUrls.values()) {
				for (URL u : urls) {
					if (!Constants.EMPTY_PROTOCOL.equals(u.getProtocol())) {
						result.add(u);
					}
				}
			}
		} else {
			final AtomicReference<List<URL>> reference = new AtomicReference<List<URL>>();
			NotifyListener listener = new NotifyListener() {
				public void notify(List<URL> urls) {
					reference.set(urls);
				}
			};
			subscribe(url, listener); // Subscribe logic guarantees the first notify to return
			List<URL> urls = reference.get();
			if (urls != null && urls.size() > 0) {
				for (URL u : urls) {
					if (!Constants.EMPTY_PROTOCOL.equals(u.getProtocol())) {
						result.add(u);
					}
				}
			}
		}
		return result;
	}

	public Map<URL, Map<String, List<URL>>> getNotified() {
		return notified;
	}

}
