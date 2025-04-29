public class Clock {
    // Class constants
    private static final byte MINS_PER_HOUR = 60;
    private static final byte HOURS_PER_DAY = 24;
    private static final byte MAX_MINUTE = MINS_PER_HOUR - 1;
    private static final byte MAX_HOUR = HOURS_PER_DAY - 1;

    // Instance variables
    private byte hour;
    private byte minute;

    public Clock(int h, int m) {
        if (h < 0 || h > MAX_HOUR) throw new IllegalArgumentException("Invalid hour");
        if (m < 0 || m > MAX_MINUTE) throw new IllegalArgumentException("Invalid minute");
        hour = (byte) h;
        minute = (byte) m;
    }

    public Clock(String s) {
        if (s == null || s.length() != 5 || s.charAt(2) != ':') throw new IllegalArgumentException("Invalid format");
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        if (h < 0 || h > MAX_HOUR || m < 0 || m > MAX_MINUTE) throw new IllegalArgumentException("Invalid time");
        hour = (byte) h;
        minute = (byte) m;
    }

    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    public boolean isEarlierThan(Clock that) {
        return hour < that.hour || (hour == that.hour && minute < that.minute);
    }

    public void tic() {
        if (++minute == MINS_PER_HOUR) {
            minute = 0;
            if (++hour == HOURS_PER_DAY) hour = 0;
        }
    }

    public void toc(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Negative delta");
        
        // Convert to total minutes to handle large deltas
        int totalMinutes = hour * MINS_PER_HOUR + minute + delta;
        
        // Calculate new time with proper wrapping
        totalMinutes %= (HOURS_PER_DAY * MINS_PER_HOUR);
        if (totalMinutes < 0) {
            totalMinutes += HOURS_PER_DAY * MINS_PER_HOUR;
        }
        
        hour = (byte) (totalMinutes / MINS_PER_HOUR);
        minute = (byte) (totalMinutes % MINS_PER_HOUR);
    }

    public static void main(String[] args) {
        // Test cases that previously failed
        Clock test1 = new Clock(12, 59);
        test1.toc(120);
        System.out.println("12:59 + 120 minutes = " + test1);  // Should be 14:59

        Clock test2 = new Clock(22, 59);
        test2.toc(1440);
        System.out.println("22:59 + 1440 minutes = " + test2);  // Should be 22:59

        Clock test3 = new Clock(23, 59);
        test3.toc(100);
        System.out.println("23:59 + 100 minutes = " + test3);  // Should be 01:39

        Clock test4 = new Clock(0, 0);
        test4.toc(30000);
        System.out.println("00:00 + 30000 minutes = " + test4);  // Should be 20:00
    }
}