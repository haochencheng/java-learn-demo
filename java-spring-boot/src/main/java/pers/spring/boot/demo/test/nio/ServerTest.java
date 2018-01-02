package pers.spring.boot.demo.test.nio;




/**
 * 服务端demo代码: Created by cc on 2017/11/1
 */
public class ServerTest {

	public static void main(String[] args) {
		server();
	}

	public static void server() {
//		ServerSocketChannel channel = null;
//		try {
//			Selector selector = Selector.open();
//			channel = ServerSocketChannel.open();
//			channel.configureBlocking(false);
//			channel.socket().setReuseAddress(true);
//			channel.bind(new InetSocketAddress(8020));
//			channel.register(selector, SelectionKey.OP_ACCEPT, new Integer(1));
//			while (true) {
//				if (selector.select() > 0) {
//					Set<SelectionKey> selectedKeySet = selector.selectedKeys();
//					selectedKeySet.forEach(key -> {
//						selectedKeySet.remove(key);
//						if (key.isAcceptable()) {
//							key.attach(new Integer(1));
//							try {
//								SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//						if (key.isReadable()) {
//							SocketChannel socketChannel = (SocketChannel) key.channel();
//							ByteBuffer buffer = ByteBuffer.allocate(1024);
//							ByteOutputStream outputStream = new ByteOutputStream();
//							int len = 0;
//							try {
//								while ((len = socketChannel.read(buffer)) != 0) {
//									buffer.flip();
//									byte[] bytes = new byte[buffer.remaining()];
//									buffer.get(bytes);
//									outputStream.write(bytes);
//									buffer.clear();
//								}
//								String str = new String(outputStream.getBytes());
//								key.attach(str);
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//						if (key.isWritable()) {
//							Object object = key.attachment();
//							String attach = object != null ? "server replay: " + object.toString() : "server replay: ";
//							SocketChannel socketChannel = (SocketChannel) key.channel();
//							try {
//								socketChannel.write(ByteBuffer.wrap(attach.getBytes()));
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//
//					});
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (channel != null) {
//				try {
//					channel.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

	}

}
