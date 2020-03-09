package com.rxing.medicinebox;
import com.amazon.ask.AlexaSkill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.rxing.medicinebox.handlers.*;


public class MedicineBoxStreamHandler extends SkillStreamHandler {

    private static AlexaSkill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new AddMedicationIntentHandler(),
                        new GetSingleMedicationIntentHandler(),
                        new GetAllMedicationsIntentHandler())
                .withSkillId("amzn1.ask.skill.24d4bcd0-71f1-4594-bafc-519cbb6e0c25")
                .withTableName("MedicineBox")
                .withAutoCreateTable(true)
                .build();
    }

    public MedicineBoxStreamHandler() {
        super(getSkill());
    }
}
