package com.switchfully.domain.user.feature;

import com.switchfully.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FeatureTest {
    @Test
    void getFeatureForCustomer_returnsListOfAllFeatures() {
        User user = testUserBuilder().buildCustomer();
        List<Feature> actualResult = Feature.getFeaturesForRoles(newArrayList(user.getRole().toString()));
        List<Feature> expectedResult = List.of(Feature.GET_ITEM_BY_ID, Feature.ORDER_ITEM);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getFeatureForAdmin_returnsListOfAllFeatures() {
        User user = testUserBuilder().buildAdmin();
        List<Feature> actualResult = Feature.getFeaturesForRoles(newArrayList(user.getRole().toString()));
        List<Feature> expectedResult = List.of(Feature.ADD_ITEM, Feature.VIEW_CUSTOMERS);
        assertEquals(expectedResult, actualResult);
    }
}