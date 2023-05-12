package com.example.dataflow.externalapi;

import com.google.cloud.aiplatform.v1beta1.MatchServiceClient;
import com.google.cloud.aiplatform.v1beta1.FindNeighborsRequest;
import com.google.cloud.aiplatform.v1beta1.FindNeighborsResponse;
import com.google.cloud.aiplatform.v1beta1.IndexEndpointName;
import java.util.ArrayList;
public class METest {
  public static void main(String[] args) {
    try (MatchServiceClient matchServiceClient = MatchServiceClient.create()) {
      FindNeighborsRequest request =
          FindNeighborsRequest.newBuilder()
              .setIndexEndpoint(
                  IndexEndpointName.of("vertex-samples-for-ck", "us-west1", "2017559856503848960").toString())
              .setDeployedIndexId("sampleDeployedIndex")
              .addAllQueries(new ArrayList<FindNeighborsRequest.Query>())
              .setReturnFullDatapoint(true)
              .build();
      FindNeighborsResponse response = matchServiceClient.findNeighbors(request);
    }catch(Exception e){
      System.out.println("Error: " + e);
    }
  }
}
