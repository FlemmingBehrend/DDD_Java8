package dk.feb.ddd.interfaces.car.rest;

import dk.feb.ddd.domain.model.car.Brand;
import dk.feb.ddd.domain.model.car.Car;
import dk.feb.ddd.domain.model.car.CarId;
import dk.feb.ddd.domain.model.car.CarRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;

import static dk.feb.ddd.domain.common.validation.Validator.validate;


@Path("/cars")
@Stateless
@Produces("application/json")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @GET
    @Path("/{id}")
    public Response retrieveCarById(@PathParam("id") String id) {
        Car car = carRepository.find(validate(new CarId(id)));
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("carId", car.getId().getValue());
        builder.add("brand", car.getBrand().name());
        car.getTopSpeed().ifPresent(ts -> builder.add("topSpeed", ts.getValue()));
        car.getColor().ifPresent(c -> {
            JsonObjectBuilder colorBuilder = Json.createObjectBuilder();
            colorBuilder.add("value", c.getValue());
            colorBuilder.add("metal", c.getMetal());
            builder.add("color", colorBuilder);
        });
        car.getPrice().ifPresent(p -> builder.add("price", p));
        return Response.ok(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateCar(@PathParam("id") String id, JsonObject payload, @Context UriInfo info) {
        Car car = new Car.Factory(new CarId(id), Brand.valueOf(payload.getString("brand")))
                .withTopSpeed(payload.getInt("topSpeed"))
                .withPrice(BigDecimal.valueOf(payload.getInt("price")))
                .withColor(payload.getJsonObject("color").getString("value"), payload.getJsonObject("color").getBoolean("metal"))
                .build();
        carRepository.update(car);
        return Response.status(Response.Status.NO_CONTENT).header("Location", info.getAbsolutePath()).build();
    }

}