import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "list_set.Airplane")
public class Airplane implements IAction, IProperty, Serializable, Comparable<Airplane> {

    private float kerosene;
    private String maintenance;
    private String insurance;
    private float flightCrewCost;
    private AirplaneType airplaneType; // has-a

    private int id_airplane;
    private float distance;

    private static List<Airplane> listaAvioane = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public Airplane() {
    }

    //exception handling: constructor + setteri
    public Airplane(float kerosene, String maintenance, String insurance, float flightCrewCost, AirplaneType airplaneType) throws Exception {
        if (kerosene <= 0 || flightCrewCost <= 0) {
            throw new Exception("Kerosene or flight crew cost must be positive");
        }
        this.kerosene = kerosene;
        this.maintenance = maintenance;
        this.insurance = insurance;
        this.flightCrewCost = flightCrewCost;
        this.airplaneType = airplaneType;
    }
    @XmlElement
    public float getKerosene() {
        return kerosene;
    }

    public void setKerosene(float kerosene) {
        this.kerosene = kerosene;
    }
    @XmlElement
    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) throws Exception {
        if (maintenance == null) {
            throw new Exception("Maintenance cannot be null!");
        }
        this.maintenance = maintenance;
    }
    @XmlElement
    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
    @XmlElement
    public float getFlightCrewCost() {
        return flightCrewCost;
    }

    public void setFlightCrewCost(float flightCrewCost) {
        this.flightCrewCost = flightCrewCost;
    }
    @XmlElement
    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    @Override
    public void takeOff() {
        if (hasSufficientFuel(this.kerosene) && hasSufficientCrewCost(this.flightCrewCost) && isMaintenanceComplete(this.maintenance)) {
            System.out.println("The plane is about to take off");
        } else {
            System.out.println("The plane cannot take off due to insufficient resources or incomplete maintenance");
        }
    }

    @Override
    public boolean isLuxury() {
        return this.airplaneType.equals(AirplaneType.TURKISH_AIRLINES) && this.flightCrewCost > 5000;
    }

    public static List<Airplane> getListaAvioane() {
        return listaAvioane;
    }

    public static void setListaAvioane(List<Airplane> listaAvioane) {
        Airplane.listaAvioane = listaAvioane;
    }

    @Override
    public String toString() {
        return "list_set.Airplane{" +
                "kerosene=" + kerosene +
                ", maintenance='" + maintenance + '\'' +
                ", insurance='" + insurance + '\'' +
                ", flightCrewCost=" + flightCrewCost +
                ", airplaneType=" + airplaneType + "\n";
    }

    //serializare -> scriere in binar
    public static void serialization(String fileName, List<Airplane> listaAvioane) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for(Airplane airplane : listaAvioane) {
                objectOutputStream.writeFloat(airplane.getKerosene());
                objectOutputStream.writeObject(airplane.getMaintenance());
                objectOutputStream.writeObject(airplane.getInsurance());
                objectOutputStream.writeFloat(airplane.getFlightCrewCost());
                objectOutputStream.writeObject(airplane.getAirplaneType());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //deserealizare -> citirea din binar
    public static List<Airplane> deserealization(String inputBinaryFile) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inputBinaryFile)))) {

            while (true) {
                try {
                    Airplane airplane = new Airplane();
                    airplane.setKerosene(objectInputStream.readFloat());
                    airplane.setMaintenance((String) objectInputStream.readObject());
                    airplane.setInsurance((String) objectInputStream.readObject());
                    airplane.setFlightCrewCost(objectInputStream.readFloat());
                    airplane.setAirplaneType((AirplaneType) objectInputStream.readObject());
                    listaAvioane.add(airplane);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaAvioane;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Float.compare(kerosene, airplane.kerosene) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kerosene, maintenance, insurance, flightCrewCost, airplaneType, id_airplane, distance);
    }

    @Override
    public int compareTo(Airplane o) {
        //sortare dupa kerosene
        //daca 2 obiecte au acelasi kerosene, sortam creascator dupa flightCrewCost

        if(this.kerosene < o.kerosene)
        {
            return -1;
        }
        else if(this.kerosene > o.kerosene) {
            return 1;
        }
        else //if(this.kerosene == o.kerosene)
        {
            if(this.flightCrewCost < o.flightCrewCost)
            {
                return -1;
            }
            else if(this.flightCrewCost > o.flightCrewCost)
            {
                return 1;
            }
            else //return 0;
            {
                //pt String insurance ; e LA FEL pentru orice alt tip de obiect(Date, Integer, Double, Float, Boolean, Char) - astea sunt obiecte de tip Wrapper
                int comparareInsurance = this.insurance.compareTo(o.insurance);
                if(comparareInsurance!=0)
                {
                    return comparareInsurance;
                }
                else return 0;
            }
        }


    }
}