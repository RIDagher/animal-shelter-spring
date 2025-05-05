Animal Shelter Project – Assignment 2


This is an enhanced version of the Animal Shelter system for Assignment 2.

Key Features:
- Abstract class: Animal (with Dog, Cat, Bird subclasses)
- Interface: Adoptable (implemented by animal classes)
- New classes: AdoptionForm, MedicalRecord, Volunteer
- Includes: task assignment, adoption approval, medical entry recording

Composition/Aggregation:
- Animal has-a MedicalRecord (composition)
- AdoptionForm uses-an Animal (composition)
- Volunteer has-a schedule and task list (aggregation)

Instructions:
1. Compile all files in `src/`
2. Run `AnimalShelterManagement` to test features
3. Example scenarios include: adopting animals, adding treatments, assigning volunteer tasks