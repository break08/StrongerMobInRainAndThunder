@Mixin(Pillager.class)
public class PillagerNoAvoidCreaking(){
  @Inject(method = "registerGoals", at = @At ("HEAD"), cancellable = true)
  private void registerGoals (CallbackInfo ci){
    ci.cancel();
  }
}
