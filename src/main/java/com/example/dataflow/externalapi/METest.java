package com.example.dataflow.externalapi;

import com.google.cloud.aiplatform.v1beta1.MatchServiceClient;
import com.google.cloud.aiplatform.v1beta1.FindNeighborsRequest;
import com.google.cloud.aiplatform.v1beta1.FindNeighborsResponse;
import com.google.cloud.aiplatform.v1beta1.MatchServiceSettings;
import com.google.cloud.aiplatform.v1beta1.IndexEndpointName;

import java.util.ArrayList;
import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.Credentials;
import com.google.api.gax.core.FixedCredentialsProvider;
public class METest {
  public static void main(String[] args) {

    try {
      Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/home/asunwoo_mikewood_altostrat_com/vertex-samples-for-ck-0b622d103d10.json"));

      MatchServiceSettings matchServiceSettings =
          MatchServiceSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();

      MatchServiceClient matchServiceClient = MatchServiceClient.create(matchServiceSettings);

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
