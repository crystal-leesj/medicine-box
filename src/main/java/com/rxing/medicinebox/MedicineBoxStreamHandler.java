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
                        new ConnectionsResponseHandler())
                .withSkillId("amzn1.ask.skill.25d225d1-b56a-4ba5-9d0d-04b6eaafa80f")
                .withTableName("MedicineBox")
                .withAutoCreateTable(true)
                .build();
    }

    public MedicineBoxStreamHandler() {
        super(getSkill());
    }
}
