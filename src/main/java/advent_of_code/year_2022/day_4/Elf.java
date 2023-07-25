package advent_of_code.year_2022.day_4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Elf {
    private int firstSectionId;
    private int lastSectionId;

    public boolean sectionsRangeFullyContainsOtherElfSectionsRange(Elf otherElf) {
        return this.firstSectionId <= otherElf.getFirstSectionId() && this.lastSectionId >= otherElf.getLastSectionId();
    }
}
