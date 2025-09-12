class Triangle implements Shape{
    private int height;
    private int base;
    @Override
    public int area() {
        return base * height / 2;
    }

    @Override
    public int perimeter() {
        return 0;
    }
}