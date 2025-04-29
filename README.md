# Clock Data Type Implementation

## What This Program Does

This Java class implements a 24-hour clock data type that handles time operations including:
- Time storage and representation
- Time comparisons
- Minute addition (both single and multiple minutes)
- Input validation

## Key Features

- **Two Constructors**:
  - `Clock(int h, int m)` - creates from hours/minutes
  - `Clock(String s)` - creates from "HH:MM" string
- **Time Operations**:
  - `tic()` - adds one minute
  - `toc(int delta)` - adds multiple minutes
- **Comparison**:
  - `isEarlierThan(Clock that)` - time comparison
- **Validation**:
  - Ensures all times are valid (00:00-23:59)
  - Rejects invalid inputs with exceptions

## Time Specifications

| Component | Range    | Format |
|-----------|----------|--------|
| Hours     | 00-23    | 2 digits |
| Minutes   | 00-59    | 2 digits |
| String    | HH:MM    | Colon-separated |

## How To Use

1. **Create Clock Objects**:
   ```java
   Clock morning = new Clock(9, 30);    // 09:30
   Clock midnight = new Clock("00:00"); // 00:00
   ```

2. **Modify Time**:
   ```java
   morning.tic();      // Advances by 1 minute
   morning.toc(15);    // Advances by 15 minutes
   ```

3. **Compare Times**:
   ```java
   if (morning.isEarlierThan(midnight)) {
       System.out.println("Morning comes before midnight");
   }
   ```

4. **String Representation**:
   ```java
   System.out.println(morning); // Prints "09:45"
   ```

## Edge Case Handling

- **Time Overflow**:
  - 23:59 + 1 minute = 00:00
  - 15:45 + 120 minutes = 17:45
- **Input Validation**:
  - Rejects 24:00, -01:30, 12:60
  - Rejects malformed strings ("9:5", "12-30")

## Example Usage

```bash
# Compile and run test cases
javac Clock.java
java Clock
```

## Applications

- Time tracking systems
- Scheduling applications
- Alarm clock implementations
- Time-based simulations
- Digital clock displays

## Performance Characteristics

- All operations execute in constant time O(1)
- Minimal memory footprint
- Efficient for high-frequency time operations

## Limitations

- 24-hour format only (no AM/PM support)
- Minute precision only (no seconds)
- No timezone support
- No date handling

*Note: For complete calendar functionality, consider extending with Java's built-in time classes.*
