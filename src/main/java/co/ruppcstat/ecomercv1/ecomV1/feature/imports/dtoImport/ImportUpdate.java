package co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport;

import java.time.LocalDate;

public record ImportUpdate(
        String codeNumber,
        String importDate,
        Float totalAmount

) {
}
