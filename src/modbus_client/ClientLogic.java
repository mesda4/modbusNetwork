package modbus_client;

import java.io.IOException;

import de.re.easymodbus.modbusclient.ModbusClient;

public class ClientLogic {

    // Initial client setup
    public static void clientSetup(ModbusClient client, String ip, String stringPort) throws Exception {
        int port = 0;

        // If IP address mathes regex "0.0.0.0"
        if (ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}"))
            client.setipAddress(ip);
        else
            throw new Exception("INVALID IP ADDRESS");

        // If text in portField is an integer
        if (stringPort.matches("\\d+"))
            port = Integer.parseInt(stringPort);
        else
            throw new Exception("INVALID PORT NUMBER");

        // If port is in registered or private range
        if (port > 1023 && port < 65536)
            client.setPort(port);
        else
            throw new Exception("INVALID PORT NUMBER");
    }

    // Connect client to specified server
    public static void connectClient(ModbusClient client) throws Exception {
        try {
            client.Connect();
        } catch (IOException e) {
            System.out.println(e);
            throw new Exception("HOST IS NOT FOUND");
        }
    }

    // Disconnect client from specified server
    public static void disconnectClient(ModbusClient client) throws Exception {
        try {
            client.Disconnect();
        } catch (IOException e) {
            throw new Exception("UNRESOLVED ERROR");
        }
    }

    // Change specified coil state to "true"
    public static void setCoil(ModbusClient client, int coil) {
        try {
            client.WriteSingleCoil(coil, true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Change specified coil state to "false"    
    public static void unsetCoil(ModbusClient client, int coil){
        try {
            client.WriteSingleCoil(coil, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}