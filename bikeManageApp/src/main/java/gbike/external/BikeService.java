
package gbike.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

//@FeignClient(name="bike", url="http://localhost:8088")
@FeignClient(name="bike", url="${api.url.bikeservice}")
public interface BikeService {

    @RequestMapping(method= RequestMethod.GET, path="/bikes/getReportAndUpdate")
    public boolean getReportAndUpdate(@RequestParam("bikeid") Long bikeid, @RequestParam("batterylevel") Integer batterylevel, @RequestParam("usableyn") Boolean usableyn);
}