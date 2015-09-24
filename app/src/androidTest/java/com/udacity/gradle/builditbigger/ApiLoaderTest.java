package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.tasks.ApiLoader;

/**
 * @author Julio Mendoza on 9/24/15.
 */
public class ApiLoaderTest extends LoaderTestCase {

    public void testApiLoader()
    {
        String result = getLoaderResultSynchronously(new ApiLoader(getContext()));

        /**
         * Asserts that the result is not an empty string.
         */
        assertTrue(!result.isEmpty());
    }
}
