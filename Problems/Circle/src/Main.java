class Circle {

    double radius;

    // write methods here
    double getLength() {
        return 2 * this.radius * Math.PI;
    }
    double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}