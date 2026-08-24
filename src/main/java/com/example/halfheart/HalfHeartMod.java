package com.example.halfheart;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;

public class HalfHeartMod implements ModInitializer {

    // 마인크래프트 체력은 하트 1개당 2.0f 이므로, 반 칸(半) = 1.0f
    private static final float MIN_HEALTH = 1.0f;

    @Override
    public void onInitialize() {
        // 모든 대미지(낙사, 화상, 몬스터 공격 등) 발생 직전에 호출되는 이벤트
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            // 플레이어가 아니면 그대로 통과 (몬스터/동물은 원래대로 죽음)
            if (!(entity instanceof PlayerEntity player)) {
                return true;
            }

            float currentHealth = player.getHealth();

            // 이미 반 칸 이하라면 대미지 자체를 무효화
            if (currentHealth <= MIN_HEALTH) {
                return false;
            }

            float resultingHealth = currentHealth - amount;

            // 이번 대미지로 반 칸 밑으로 떨어질 것 같으면
            // 체력을 정확히 반 칸으로 고정하고, 원래 대미지 처리는 취소
            if (resultingHealth < MIN_HEALTH) {
                player.setHealth(MIN_HEALTH);
                return false;
            }

            // 그 외에는 평소처럼 대미지 적용
            return true;
        });
    }
}
