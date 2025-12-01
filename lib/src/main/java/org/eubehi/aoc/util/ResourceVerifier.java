/*
 * Copyright Eubehi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eubehi.aoc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Creates a path from a resource, ensuring it actually exist */
public final class ResourceVerifier {
    private static final Logger log = LoggerFactory.getLogger(ResourceVerifier.class);

    private ResourceVerifier() {
        // Utility class
    }

    /**
     * @param resource The path to the resource of the year 2025
     *
     * @return An existing path for the year 2025
     */
    public static Path y2K25(final String resource) {
        final String res = "/Y2K25/" + resource;
        final URL url = ResourceVerifier.class.getResource(res);
        if (url == null) throw new IllegalArgumentException("Unable to find resource '" + res + "'");

        try {
            return Paths.get(url.toURI());
        } catch (final URISyntaxException e) {
            throw new RuntimeException("Could not convert URL '" + url + "' to URI", e);
        }
    }
}
