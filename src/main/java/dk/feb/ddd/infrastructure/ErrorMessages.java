package dk.feb.ddd.infrastructure;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ErrorMessages {

    public enum Type {
        UNKNOWN_ERROR("unknown-error"),
        INVALID_INPUT("invalid-input"),
        ENTITY_NOT_FOUND("entity-not-found"),
        ROLLBACK("rollback"),
        ACCESS_DENIED("access-denied"),
        PERSISTENCE_ERROR("persistence-error"),
        VALIDATION_CONSTRAINT("validation-constraint"),
        OPTIMISTIC_LOCK("optimistic-lock");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static JsonObject createErrorObject(Type type, String message, String detailedMessage) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("type", type.getName());
        jsonObjectBuilder.add("message", message);
        jsonObjectBuilder.add("details", detailedMessage);
        return jsonObjectBuilder.build();
    }

}