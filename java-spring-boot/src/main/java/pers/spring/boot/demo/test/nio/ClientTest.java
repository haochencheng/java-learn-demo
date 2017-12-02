package pers.spring.boot.demo.test.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端demo代码 Created by cc on 2017/11/1
 */
public class ClientTest {

	public static void main(String[] args) {
		System.out.println(16 >>> 2);
		// client();
	}

	public static void client() {
		SocketChannel channel = null;

		try {
			Selector selector = Selector.open();
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(8020));
			channel.register(selector, SelectionKey.OP_CONNECT);

			while (true) {
				if (selector.select() > 0) {
					Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
					selectionKeyIterator.forEachRemaining(key -> {
						selectionKeyIterator.remove();

						SocketChannel ch = (SocketChannel) key.channel();
						new Thread(new ClientRunnable(ch)).start();
						if (key.isConnectable()) {
							try {
								ch.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, new Integer(1));
								ch.finishConnect();
							} catch (ClosedChannelException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						if (key.isReadable()) {
							key.attach(new Integer(1));
							ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							int len = 0;
							try {
								while ((len = ch.read(buffer)) != 0) {
									buffer.flip();
									byte[] bytes = new byte[buffer.remaining()];
									buffer.get(bytes);
									outputStream.write(bytes);
									buffer.clear();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}

							if (key.isWritable()) {
								key.attach(new Integer(1));
								try {
									ch.write(ByteBuffer.wrap((("client say:hi")).getBytes()));
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

					});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	static class ClientRunnable implements Runnable {

		private SocketChannel ch;

		private ClientRunnable(SocketChannel ch) {
			this.ch = ch;
		}

		@Override
		public void run() {
			try {
				while (true) {
					ch.write(ByteBuffer.wrap((("client say:hi")).getBytes()));
					Thread.sleep(3000);
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					ch.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
