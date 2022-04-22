package com.afd.mate.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-22T14:41:08.768562Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.stockPositionAndMarketValue.base-path:/v2}")
public class AutoStockPositionMarketValueApiController implements AutoStockPositionMarketValueApi {

    private final AutoStockPositionMarketValueApiDelegate delegate;

    public AutoStockPositionMarketValueApiController(@Autowired(required = false) AutoStockPositionMarketValueApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new AutoStockPositionMarketValueApiDelegate() {});
    }

    @Override
    public AutoStockPositionMarketValueApiDelegate getDelegate() {
        return delegate;
    }

}
