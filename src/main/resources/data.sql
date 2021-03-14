INSERT INTO user(name, email, password) VALUES('Chang', 'chang@gmail.com','$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne');
INSERT INTO profile(name) VALUES('DOCTOR');
INSERT INTO user_profilesS(user_id, profiles_id) VALUES((SELECT id FROM user LIMIT 1), (SELECT id FROM profile LIMIT 1));
INSERT INTO cases(electronic_health_record,date_create,state) VALUES("Patient  is an 45 year old  female.    Chief Complaint:  Problem    HPI  states that about one month ago she woke up with redness and swelling to her left eye.  She went to see an ophthalmologist who prescribed her naphazoline.  She states that this relieves the redness only temporarily.  She also states that this morning she awoke with more crusting to the left eye.  The eye is not particularly itchy, but seems more irritated today.  She has not had any sick contacts.          Review of Systems   Constitutional: Negative for fever.   Eyes: Positive for discharge and redness. Negative for blurred vision, double vision and photophobia.   Skin: Negative for itching.   Neurological: Positive for headaches.         Objective:     BP 100/69  -Strict ER precautions reviewed with patient should symptoms persist or worsen (specific signs reviewed verbally).  Good communication established and plan agreed upon by patient."
, DATE_ADD(NOW(), INTERVAL -3 DAY),
"NOT_LABELLED");
INSERT INTO cases(electronic_health_record,date_create,state) VALUES("Patient presents with Flank Pain. The patient is a 51-year-old female, no significant past medical history, presents to the emergency department with left-sided flank pain ongoing ×1 month now with abdominal pain. The pain is intermittent, but has been worsening. She reports new onset nausea, vomiting, diarrhea for the last 2 days. She reports multiple episodes of nonbloody emesis starting yesterday. She has also had multiple episodes of nonbloody diarrhea. She has gone to see her primary care doctor twice since symptoms began. She was found to have mildly elevated creatinine and was referred to a nephrologist. However, the nephrologist is not willing to see her until . The patient feels she cannot wait that long especially in light of these new symptoms. She then followed up with her primary care doctor again and he prescribed Zofran and loperamide but offered her no other solutions. The pain has since increased as well. She denies any fevers, chills. She denies urinary symptoms including burning with urination, frequency, hematuria.",
DATE_ADD(NOW(), INTERVAL -2 DAY),
"NOT_LABELLED");
INSERT INTO cases(electronic_health_record,date_create, state) VALUES("Patient  is an 42 year old  male.    Chief Complaint: Establish Care and Physical    HPI      Hemorrhoids  Bothersome  Comes and goes  Especially with sedentary life style  Recently worse  Couple nights where almost wakes patient up  Gets intermittently constipated  High fiber diet    Patient Active Problem    Diagnoses Code
 -  Hemorrhoids 455.6E       No outpatient prescriptions have been marked as taking for the  encounter (Office Visit) with ,  C.     Allergies   Allergen Reactions
 -  Pcn (Penicillins)
 -  Morphine        No past medical history on file.  Past Surgical History   Procedure Date
 -  Hx knee surgery      Arthroscopy age 15 for torn meniscus       Family History   Problem Relation  of Onset
 -  Cancer Mother      Breast
 -  Hypertension Mother
 -  Hypertension Father      History   Substance Use Topics
 -  Smoking status: Never Smoker
 -  Smokeless tobacco: Not on file",
 DATE_ADD(NOW(), INTERVAL -1 DAY),
 "NOT_LABELLED"
);