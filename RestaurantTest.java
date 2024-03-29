import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)


class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

   @BeforeEach
   public void setup(){
       LocalTime openingTime = LocalTime.parse("10:30:00");
       LocalTime closingTime = LocalTime.parse("22:00:00");
       restaurant =Mockito.spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));
       restaurant.addToMenu("Sweet corn soup",119);
       restaurant.addToMenu("Vegetable lasagne", 269);

   }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Mockito.doReturn(LocalTime.parse("17:00:00")).when(restaurant).getCurrentTime();
        assertTrue(restaurant.isRestaurantOpen());


        //WRITE UNIT TEST CASE HERE

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Mockito.doReturn(LocalTime.parse("23:00:00")).when(restaurant).getCurrentTime();
        assertFalse(restaurant.isRestaurantOpen());

        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void return_the_total_cost_of_selected_items(){
       assertEquals(119+269,restaurant.totalCostOfSelectedItems("Sweet corn soup","Vegetable lasagne"));
    }
}