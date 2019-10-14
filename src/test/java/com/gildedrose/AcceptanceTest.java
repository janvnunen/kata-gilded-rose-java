package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class AcceptanceTest
{
    @Test
    public void thirtyDays() throws IOException {
        final String[] lines =
            FileUtils.readLines(new File("ThirtyDays.txt"), "UTF-8").toArray(new String[]{});

        final PrintStream originalOut = System.out;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Main.main(new String[]{});

        System.out.flush();
        System.setOut(originalOut);

        final String mainOut = baos.toString();
        final String[] outputLines = mainOut.split(System.getProperty("line.separator"));
        for (int i = 0; i < Math.min(lines.length, outputLines.length); i++) {
            assertEquals(lines[i], outputLines[i]);
        }
    }
}
