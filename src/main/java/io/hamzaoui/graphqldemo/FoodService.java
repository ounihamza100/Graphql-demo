package io.hamzaoui.graphqldemo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * In GraphQL you can either define a query which will only load data,
 * or define a mutation which will also change the underlying data that feeds the API.
 *  For this sample app, we will define the basic read, save and delete functionality for food entities.
 *
 * @author Hamza Ouni
 */
@Service
@GraphQLApi
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GraphQLQuery(name = "foods") // READ ALL
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    @GraphQLQuery(name = "food") // READ BY ID
    public Optional<Food> getFoodById(@GraphQLArgument(name = "id") Long id) {
        return foodRepository.findById(id);
    }

    @GraphQLMutation(name = "saveFood") // CREATE
    public Food saveFood(@GraphQLArgument(name = "food") Food food) {
        return foodRepository.save(food);
    }

    @GraphQLMutation(name = "deleteFood") // DELETE
    public void deleteFood(@GraphQLArgument(name = "id") Long id) {
        foodRepository.deleteById(id);
    }

    @GraphQLQuery(name = "isGood") // Calculated property of Food
    public boolean isGood(@GraphQLContext Food food) {
        return !Arrays.asList("Avocado", "Spam").contains(food.getName());
    }

}
