package org.e2cho.e2cho_shopping_mall.dto.order.orderHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderHistoryDelete {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long deletedOrderSheetId;

        public static Dto fromEntity(Long deletedOrderSheetId){

            return Dto.builder()
                    .deletedOrderSheetId(deletedOrderSheetId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String message;
        private Long deletedOrderSheetId;

        public static Response fromDto(Dto dto){

            return Response.builder()
                    .message("선택한 주문내역이 삭제되었습니다.")
                    .deletedOrderSheetId(dto.getDeletedOrderSheetId())
                    .build();
        }
    }
}
