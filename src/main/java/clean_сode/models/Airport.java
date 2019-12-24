package clean_сode.models;

import clean_сode.models.planes.ExperimentalPlane;
import clean_сode.models.planes.MilitaryPlane;
import clean_сode.models.planes.PassengerPlane;
import clean_сode.models.planes.Plane;
import clean_сode.models.service.SortType;

import java.util.*;

/**
 * @author Vitali Shulha
 * @version 1.1, 01/04/19
 */

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType militaryType) {
        List<MilitaryPlane> militaryPlanesByType = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane militaryPlane : militaryPlanes) {
            if (militaryPlane.getType() == militaryType) {
                militaryPlanesByType.add(militaryPlane);
            }
        }
        return militaryPlanesByType;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> ExperimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                ExperimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return ExperimentalPlanes;
    }

    public Airport sortByCondition(SortType sortType) {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                switch (sortType) {
                    case MAX_SPEED:
                        return o1.getMaxSpeed() - o2.getMaxSpeed();
                    case MAX_DISTANCE:
                        return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
                    case MAX_LOAD_CAPACITY:
                        return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
                }
                return 0;
            }
        });
        return this;
    }

    @Override
    public String toString() {
        return "models.Airport{" +
                "planes=" + planes.toString() +
                '}';
    }
}