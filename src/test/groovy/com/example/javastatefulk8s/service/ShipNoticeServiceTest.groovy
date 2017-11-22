package com.example.javastatefulk8s.service

import com.example.javastatefulk8s.DemoApplicationTests
import com.example.javastatefulk8s.model.ShipNotice
import com.example.javastatefulk8s.model.ShipNoticeBuilder
import com.example.javastatefulk8s.model.ShipNoticesBatch
import com.example.javastatefulk8s.model.ShipNoticesBatchBuilder
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.junit.Before
import org.junit.Test

import javax.ws.rs.core.MediaType
import java.time.Instant
import java.time.ZoneOffset


import static io.restassured.RestAssured.given
import static io.restassured.RestAssured.get
import static org.hamcrest.Matchers.equalTo

/**
 * Created by ravipalakodeti on 11/21/17.
 */
@Slf4j
class ShipNoticeServiceTest extends DemoApplicationTests {


    private static final Random random = new Random()
    private static final String SHIP_NOTICE_URL = "/ship-notices"
    private static final makes = ['FORD', 'CHRYSLER', 'GM', 'TOYOTA', 'BMW']
    private static final int vinCollectionSize = 10000

    @Before
    void setup() {
        RestAssured.port = localServerPort
        // use this for checking against a running application instead of a test runtime
//         RestAssured.port = 8080
    }


    @Test
    void shipServiceIsUp() {
        get("${SHIP_NOTICE_URL}/health")
        .then()
        .statusCode(HttpStatus.SC_OK)
    }

    @Test
    void loadShipNotice() {

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body( this.createShipNoticeBatch() )
            .when()
                .put(SHIP_NOTICE_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                  .assertThat()
                    .body('code', equalTo('CREATED'))
    }

    private ShipNoticesBatch createShipNoticeBatch() {

        ShipNoticesBatch shipNotices = new ShipNoticesBatchBuilder()
                                        .transactionId(random.nextInt(100000000))
                                        .create()

        shipNotices.setShipNotices(
            (1..10000).collect {
                new ShipNoticeBuilder()
                    .vin(it)
                    .customer(makes[random.nextInt(5)])
                    .timeOfMessageOrigin(Instant.now().atOffset(ZoneOffset.UTC).toString())
                    .create()
            }
        )
        return shipNotices
    }
}
