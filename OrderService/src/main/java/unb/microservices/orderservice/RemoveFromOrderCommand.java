package unb.microservices.orderservice;

public class RemoveFromOrderCommand extends Command{
    private Product item;
    @Override
    public void execute() {
        for (int i = 0; i<Command.getOrder().getItems().size(); i++) {
            if(item.getName().equals(Command.getOrder().getItems().get(i).getName())){
                Command.getOrder().getItems().remove(i);
                break;
            }
        }
    }

    @Override
    public void undo() {
        Command.getOrder().getItems().add(item);
    }

    public void setItem(Product item) {
        this.item = item;
    }
}
