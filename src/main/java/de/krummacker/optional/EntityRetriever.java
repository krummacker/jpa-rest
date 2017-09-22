package de.krummacker.optional;

import java.util.Optional;

public class EntityRetriever {

    public Optional<String> createRandomString(int length) {
        if (length < 0 || length > 10) {
            return Optional.empty();
        } else {
            RandomString rs = new RandomString(length);
            String s = rs.nextString();
            return Optional.of(s);
        }
    }
}
