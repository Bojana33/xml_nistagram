package graph.graphservice.Model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NodeDegree implements Serializable {
    long outDegree;
    long inDegree;
}
