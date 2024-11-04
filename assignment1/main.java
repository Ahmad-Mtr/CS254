import controller.OrderController;
import model.Order;
import view.OrderView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        OrderView view = new OrderView();
        Order order = new Order();
        OrderController controller = new OrderController(order, view);
        view.setVisible(true);




    }
}


