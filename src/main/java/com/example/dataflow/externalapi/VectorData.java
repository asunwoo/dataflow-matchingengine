package org.example.dataflow.externalapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableCell;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.extensions.avro.coders.AvroCoder;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.coders.StringDelegateCoder;

@DefaultCoder(AvroCoder.class)
class VectorData {
    private static final Logger LOG = LoggerFactory.getLogger(VectorData.class);

        public List<Double> startingVector;
        public List<Double> matchedVector;

        public static VectorData fromTableRow(TableRow row) {
            VectorData data = new VectorData();
            LOG.info("What The***********************************************************************" + row.toString());
            if(null != row){
                List<Double> cellVector = (List<Double>)row.get("vector");
                LOG.info("*************" + cellVector.toString());
                data.startingVector = cellVector;

                //TODO This is a hack to prevent an NPE, likely need to separate vectordata into
                //separate objects for starting and match
                data.matchedVector = cellVector;
                // if(null != cellList && cellList.size()>0){
                //     double[] doubleList = new double[cellList.size()];
                //     for(int i=0; i< cellList.size(); i++){
                //         doubleList[i] = cellList.get(i).doubleValue();
                //         LOG.info("Value: " + doubleList[i]);
                //     }
                //     data.vector = doubleList;
                // }
            }
            return data;
        }

    public TableRow getMatchedTableRow() {
        TableRow row = new TableRow();
        if(null != this.matchedVector) {
            row.set("vector", this.matchedVector);
        }else{
            LOG.error("Error: matchedVector is null");
        }
        return row;
    }

}
// [END bigquery_my_data]