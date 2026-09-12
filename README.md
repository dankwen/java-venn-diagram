// Dan Wenger
// Java Intro Mod 3 Assignment
// NOTE: I generated this spec in Gemini NotebookLM by loading the assignment requirements and my class notes, then prompted 
// Gemini to create a README.md that we could use with AI Code Assist agents in the IDE. 

# Module 3 Assignment Specification: Generic Venn Diagram Utility Class

## Overview
This specification defines the architecture, requirements, and multi-phase implementation roadmap for **Module 3 Assignment: Venn Diagram Utility Class**.

The purpose of this project is to implement a generic data structure `VennDiagram<T>` utilizing Java Generics (`<T>`) and Java Collections Framework (`Set<T>` / `HashSet<T>`) to model a 3-circle Venn Diagram capable of set operations (Union, Intersection, Complement, and Diagram Center).

---

## Technical Specifications & Architecture

### Package Structure
All source files must reside in the package `edu.wctc` under `src/edu/wctc/`.

---

### Class Specifications: `VennDiagram<T>`

`VennDiagram<T>` is a generic class parameterized on type `T`.

#### Fields
- `private String label1` - Label for Circle 1 (e.g., "carby" or "evens")
- `private String label2` - Label for Circle 2 (e.g., "tomatoey" or "primes")
- `private String label3` - Label for Circle 3 (e.g., "cheesy" or "Fibonaccis")
- `private Set<T> circle1` - Set holding elements for Circle 1 (initialized as `HashSet<T>`)
- `private Set<T> circle2` - Set holding elements for Circle 2 (initialized as `HashSet<T>`)
- `private Set<T> circle3` - Set holding elements for Circle 3 (initialized as `HashSet<T>`)

#### Required Methods

1. **Constructor**
   ```java
   public VennDiagram(String label1, String label2, String label3)
   ```
   - Assigns `label1`, `label2`, and `label3` to their respective fields.
   - Instantiates `circle1`, `circle2`, and `circle3` as empty `HashSet<T>` instances.

2. **Helper Method: `getCircleForLabel`**
   ```java
   private Set<T> getCircleForLabel(String label)
   ```
   - Matches `label` against `label1`, `label2`, and `label3` (using `.equalsIgnoreCase()` or `.equals()`).
   - Returns the corresponding `Set<T>` (`circle1`, `circle2`, or `circle3`).
   - Returns `null` or an empty fallback set if no label matches.
   - **Crucial Rule**: Every other method in `VennDiagram` MUST delegate set retrieval through `getCircleForLabel` rather than directly accessing `circle1`, `circle2`, or `circle3`.

3. **Varargs Add Method: `add`**
   ```java
   public void add(T item, String... labels)
   ```
   - Accepts an item of generic type `T` and a varargs parameter `labels` (`String...`).
   - Loops over each label in `labels`, invokes `getCircleForLabel(label)` to retrieve the target set, and adds `item` to that set (`set.add(item)`).

4. **Union Method: `unionOf`**
   ```java
   public Set<T> unionOf(String first, String second)
   ```
   - Retrieves the sets corresponding to `first` and `second` via `getCircleForLabel`.
   - Creates a new `Set<T>` initialized with the elements of the first set.
   - Calls `.addAll(secondSet)` to perform the mathematical Union ($A \cup B$).
   - Returns the new combined set.

5. **Intersection Method: `intersectionOf`**
   ```java
   public Set<T> intersectionOf(String first, String second)
   ```
   - Retrieves the sets corresponding to `first` and `second` via `getCircleForLabel`.
   - Creates a new `Set<T>` initialized with the elements of the first set.
   - Calls `.retainAll(secondSet)` to perform the mathematical Intersection ($A \cap B$).
   - Returns the new intersection set.

6. **Relative Complement Method: `complementOf`**
   ```java
   public Set<T> complementOf(String first, String second)
   ```
   - Retrieves the sets corresponding to `first` and `second` via `getCircleForLabel`.
   - Creates a new `Set<T>` initialized with the elements of the `first` set.
   - Calls `.removeAll(secondSet)` to perform the Relative Complement ($A \setminus B$).
   - Returns the new set containing items in `first` that are NOT in `second`.

7. **Center Intersection Method: `diagramCenter`**
   ```java
   public Set<T> diagramCenter()
   ```
   - Creates a new `Set<T>` initialized with `circle1` (or via `getCircleForLabel(label1)`).
   - Calls `.retainAll(circle2)` then `.retainAll(circle3)` to find elements common to ALL THREE circles ($A \cap B \cap C$).
   - Returns the resulting center set.

---

## Strict Constraints & Anti-Patterns to Avoid
1. **Generic Isolation**: Do NOT hardcode domain-specific terms (like `"cheesy"`, `"carby"`, `"tomatoey"`, `"evens"`) inside the `VennDiagram` class. It must remain 100% domain-agnostic.
2. **Helper Method Delegation**: `getCircleForLabel` MUST be called from `add`, `unionOf`, `intersectionOf`, and `complementOf`.
3. **Immutability of Original Sets**: `unionOf`, `intersectionOf`, `complementOf`, and `diagramCenter` MUST operate on a **new copy** of the set (e.g., `new HashSet<>(set1)`), never mutating the underlying circle fields directly.

---

## Implementation Roadmap by Milestone Phase

### Phase 1: Base Requirements & Food Venn Diagram (Experiment)
- Create `src/edu/wctc/VennDiagram.java` with generic type parameter `T`.
- Create `src/edu/wctc/Main.java` driver populating `foodDiagram` (`VennDiagram<String>`).
- Test all four set operations (`unionOf`, `intersectionOf`, `complementOf`, `diagramCenter`).
- Verify expected output matching course specs.
- Commit to Git: `"Phase 1 complete - Base VennDiagram class and Food diagram driver"`.

### Phase 2: Integer Venn Diagram & Exploration (Exploration)
- In `Main.java`, instantiate a second `VennDiagram<Integer>` object for numbers `1` through `10`.
- Define labels: `"evens"`, `"primes"`, `"Fibonaccis"`.
- Hardcode the membership for numbers 1 to 10:
  - **Evens**: 2, 4, 6, 8, 10
  - **Primes**: 2, 3, 5, 7
  - **Fibonaccis**: 1, 2, 3, 5, 8
- Execute and display the four required numeric queries:
  1. Numbers that are either prime or even (`unionOf("primes", "evens")`)
  2. Numbers that are both prime and Fibonacci (`intersectionOf("primes", "Fibonaccis")`)
  3. Odd Fibonacci numbers (`complementOf("Fibonaccis", "evens")`)
  4. Numbers that are even AND prime AND Fibonacci (`diagramCenter()`)
- Commit to Git: `"Phase 2 complete - Integer Venn diagram with evens, primes, and Fibonaccis"`.

### Phase 3: Refinement & Submission (Expertise)
- Clean up any commented-out or experimental code.
- Ensure reverse TLD package `edu.wctc` is intact.
- Verify code compiles cleanly with no red squiggly syntax errors.
- Push all 3 commits to GitHub and submit repository URL on Canvas.
