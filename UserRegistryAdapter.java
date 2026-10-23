public class UserRegistryAdapter implements UserRegistry {

    private LegacyUserFeed feed;

    public UserRegistryAdapter(LegacyUserFeed feed) {
        this.feed = feed;
    }

    @Override
    public UserProfile fetchUser() {
        String raw = feed.getRawRecord();
        String[] parts = raw.split("#", -1);

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid record format");
        }

        try {
            String studentId = parts[0];
            String email = parts[1];
            int accessLevel = Integer.parseInt(parts[2]);

            if (studentId.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Invalid record format");
            }

            return new UserProfile(studentId, email, accessLevel);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid access level");
        }
    }

    public static void main(String[] args) {
        String studentId = "230103256";

        System.out.println("Student ID: " + studentId);

        LegacyUserFeed feed = new LegacyUserFeed() {
            @Override
            public String getRawRecord() {
                return studentId + "#user@college.edu#4";
            }
        };

        UserRegistryAdapter adapter = new UserRegistryAdapter(feed);

        System.out.println(adapter.fetchUser());
    }
}