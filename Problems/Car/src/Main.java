class Car {

    int yearModel;
    String make;
    int speed;

    public void accelerate() {
        this.speed += 5;
    }

    public void brake() {
        if (speed > 5) {
            this.speed -= 5;
        } else {
            this.speed = 0;
        }
    }
}