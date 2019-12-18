package cleanCode.models.planes;

import java.util.Objects;

abstract public class Plane {
    private String modelPlane;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    public Plane(String modelPlane, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.modelPlane = modelPlane;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getModelPlane() {
        return modelPlane;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int GetMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + modelPlane + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maxFlightDistance=" + maxFlightDistance +
                ", maxLoadCapacity=" + maxLoadCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return maxSpeed == plane.maxSpeed &&
                maxFlightDistance == plane.maxFlightDistance &&
                maxLoadCapacity == plane.maxLoadCapacity &&
                Objects.equals(modelPlane, plane.modelPlane);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelPlane, maxSpeed, maxFlightDistance, maxLoadCapacity);
    }
}