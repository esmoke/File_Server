@Grab(group = 'io.undertow', module = 'undertow-core', version = '2.0.29.Final')

import java.nio.file.Paths
import java.util.logging.Logger
import io.undertow.Undertow
import io.undertow.server.handlers.resource.PathResourceManager
import static io.undertow.Handlers.resource

def log = Logger.getLogger(getClass().name)

/**
 * Config for current script.
 */
def config = [
    ip  : MyIP.get(),
    port: LocalPorts.getFreePort(),
]

/**
 * Start undertow in browse mod.
 */
Undertow.builder()
    .addHttpListener(config.port, config.ip)
    .setHandler(resource(new PathResourceManager(Paths.get(".")))
    .setDirectoryListingEnabled(true))
    .build()
    .start()

log.info"""Share server started. Open link to browse and download files:
http://${config.ip}:${config.port}
"""

/**
 * Gets the local IP address.
 */
class MyIP {
    static GOOGLE_DNS = "8.8.8.8"

    static String get() {
        new DatagramSocket().withCloseable { socket ->
            socket.connect(InetAddress.getByName(GOOGLE_DNS), LocalPorts.getFreePort())
            return socket.getLocalAddress().getHostAddress()
        }
    }
}

/**
 * Encapsulation of operations with local ports.
 * By passing 0 to {@link ServerSocket} we delegate responsibility for finding free port to JVM.
 */
class LocalPorts {
    static int getFreePort() {
        new ServerSocket(0).withCloseable {
            return it.localPort
        }
    }
}
