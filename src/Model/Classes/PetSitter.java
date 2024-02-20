package Model.Classes;

public class PetSitter extends ServiceProvider {

    private static PetSitter petSitter = null;
    private boolean dogService;
    private boolean catService;
    private boolean birdsService;
    private boolean cattleService;

    PetSitter(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour,
              boolean dogService, boolean catService, boolean birdsService, boolean cattleService) {
        super(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour);
        setDogService(dogService);
        setCatService(catService);
        setBirdsService(birdsService);
        setCattleService(cattleService);
    }

    public void setDogService(boolean dogService) {
        this.dogService = dogService;
    }

    public void setCatService(boolean catService) {
        this.catService = catService;
    }

    public void setBirdsService(boolean birdsService) {
        this.birdsService = birdsService;
    }

    public void setCattleService(boolean cattleService) {
        this.cattleService = cattleService;
    }

    public boolean getDogService() {
        return dogService;
    }

    public boolean getCatService() {
        return catService;
    }

    public boolean getBirdsService() {
        return birdsService;
    }

    public boolean getCattleService() {
        return cattleService;
    }

    public static PetSitter createPetSitter(String name, String email, String password, String mobileNo, String address, String role, boolean isAvailable, int rating, String startWorkingHour, String endWorkingHour,
                                     boolean dogService, boolean catService, boolean birdsService, boolean cattleService) {
        petSitter = new PetSitter(name, email, password, mobileNo, address, role, isAvailable, rating, startWorkingHour, endWorkingHour, dogService, catService, birdsService, cattleService);
        return petSitter;
    }

    public static PetSitter getPetSitter() {
        if (petSitter != null) {
            return petSitter;
        } else {
            throw new IllegalArgumentException("PetSitter not yet created");
        }
    }
}

