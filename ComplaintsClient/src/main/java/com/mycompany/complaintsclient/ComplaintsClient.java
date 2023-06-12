/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.complaintsclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kapi1
 */
public class ComplaintsClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String status
                = client.target("http://localhost:8080/Complaints/resources/complaints/1506/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);
        System.out.println("Status: " + status);

        String complaints
                = client.target("http://localhost:8080/Complaints/resources/complaints")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println("Complaints: " + complaints);

        String complaint
                = client.target("http://localhost:8080/Complaints/resources/complaints/1506")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println("Complaint 1506: " + complaint);

        String complaintUpdate = "{\"author\":\"Jim Brown\",\"complaintDate\":\"2021-04-24\",\"complaintText\":\"Please check TV in room 242\",\"id\":1506,\"status\":\"closed\"}";
        Response response
                = client.target("http://localhost:8080/Complaints/resources/complaints/1506")
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.json(complaintUpdate));
        System.out.println("Response: " + response);

        String complaintsOpened
                = client.target("http://localhost:8080/Complaints/resources/complaints?status=open")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println("opened complaints: " + complaintsOpened);

        client.close();
    }
}
