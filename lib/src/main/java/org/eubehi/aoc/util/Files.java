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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

/** Files utilities */
public final class Files {

    private Files() {
        // Utility class
    }

    /**
     * Read the file denoted by the {@code path} and feed it to the {@code consumer}.
     *
     * @param path     The path to the GZipped file to consume
     * @param consumer The consumer that will be fed all lines of the {@code path}
     */
    public static void readGZippedFile(
            final Path path,
            final TextConsumer consumer
    ) {
        try (final InputStream fileStream = new FileInputStream(path.toFile());
             final InputStream gzipStream = new GZIPInputStream(fileStream);
             final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipStream))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                consumer.consume(line);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
