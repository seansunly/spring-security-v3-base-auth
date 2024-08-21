package co.ruppcstat.ecomercv1.ecomV1.feature.fileUpdload;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,

        String uri,

        String contentType,

        Long size
) {
}
