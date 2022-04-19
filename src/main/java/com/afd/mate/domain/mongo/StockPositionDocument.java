package com.afd.mate.domain.mongo;

import com.afd.mate.domain.model.StockPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPositionDocument extends StockPosition {
    @Id private ObjectId id;
}
