package observer;

public interface EvenementObservable {
    void ajouterObservateur(ParticipantObserver observateur);
    void supprimerObservateur(ParticipantObserver observateur);
    void notifierObservateurs(String message);
}