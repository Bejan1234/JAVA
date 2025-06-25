package person;

//interfata reprezinta un contract la clasele adera, adica clasele ce implementeaza aceasta interfata sunt obligate
//sa supradefineasca toate metodele din interfata, daca nu vor face asta atunci vor fi marcate obligatoriu ca abstracte
//interfata are de obicei metode care sunt abstract
//poate avea campuri (nerecomandat) care trebuie sa fie neaparat static si final
//toate metodele din interfata trebuie sa fie publice! pentru a putea fi suprascrie de catre clase
public interface ISkills {
    void maresteSalariu();
    double getPremiu();
}
