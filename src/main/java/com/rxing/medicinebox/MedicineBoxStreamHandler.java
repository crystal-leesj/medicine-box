package com.rxing.medicinebox;
import com.amazon.ask.AlexaSkill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.rxing.medicinebox.handlers.*;


@SuppressWarnings("rawtypes")
public class MedicineBoxStreamHandler extends SkillStreamHandler {

    private static AlexaSkill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new AddMedicationIntentHandler(),
                        new GetSingleMedicationIntentHandler(),
                        new GetAllMedicationsIntentHandler(),
                        new ListAllCommandsIntentHandler(),
                        new CreateReminderIntentHandler(),
                        new YesIntentHandler(),
                        new NoIntentHandler(),
                        new ConnectionsResponseHandler(),
                        new SessionEndedRequestHandler(),
                        new CancelStopRequestHandler(),
                        new FallbackIntentHandler())
                .withSkillId("amzn1.ask.skill.9f64aeb9-c2dd-4989-8f9c-06aae7f38282")
                .withTableName("MedicineBox")
                .withAutoCreateTable(true)
                .build();
    }

    public MedicineBoxStreamHandler() {
        super(getSkill());
    }
}
