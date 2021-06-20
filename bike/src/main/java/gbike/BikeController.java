package gbike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class BikeController {

        @Autowired
        BikeRepository bikeRepository;

        @RequestMapping(value = "/bikes/chkAndUpdateStatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
        public boolean chkAndUpdateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
                //서킷브레이커 시간지연
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
                
                String status = "";
                boolean result = false;  
                Long bikeId = Long.valueOf(request.getParameter("bikeid"));
                Bike bike = bikeRepository.findByBikeid(bikeId);

                status = bike.getStatus();
                System.out.println("Status......." + status);

                if (!isStringEmpty(status) && status.equals("사용가능")) {
                        result = true;
                        System.out.println("result......." + result);
                        
                        bike.setStatus("사용중");
                        bikeRepository.save(bike);
                }
                return result;
        }

        private boolean isStringEmpty(String str) {
                return str == null || str.isEmpty();
        }

        @RequestMapping(value = "/bikes/getReportAndUpdate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
        public boolean getReportAndUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
                System.out.println("##### /bike/getReportAndUpdate  called #####");

                String status = "";
                boolean result = false;  
                Long bikeId = Long.valueOf(request.getParameter("bikeid"));
                Integer batteryLevel = Integer.valueOf(request.getParameter("batterylevel"));
                Boolean usableYn = Boolean.valueOf(request.getParameter("usableyn"));

                Bike bike = bikeRepository.findByBikeid(bikeId);

                status = bike.getStatus();
                System.out.println("Status......." + status);

                if (!isStringEmpty(status)) {
                        //불량일 경우 불량상태 저장 및 배터리잔량 저장
                        if(!usableYn){
                                bike.setStatus("불량");
                        }
                        bike.setBatterylevel(batteryLevel);

                        bikeRepository.save(bike);

                        //정상적으로 저장되면 true 리턴
                        result = true;
                        System.out.println("result......." + result);
                }
                return result;
        }
}
