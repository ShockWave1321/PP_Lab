class ContactList(list):
    def search(self, name):
        matching_contacts = []
        for contact in self:
            if name in contact.name:
                matching_contacts.append(contact)
        return matching_contacts


class Contact:
    def __init__(self, name, email):  # constructor
        self.name = name
        self.email = email

    # echivalentul supraincarcarii operator<< din c++
    def __repr__(self):
        return "Contact({}, {})".format(self.name, self.email)


class Agenda:
    all_contacts = ContactList()

    def add_contact(self, contact):
        self.all_contacts.append(contact)

    def print_agenda(self):
        for contact in self.all_contacts:
            print(contact)


class Friend(Contact):
    def __init__(self, name, email, phone):
        super().__init__(name, email)
        self.phone = phone

    def __repr__(self):
        return "Contact({}, {}, {})".format(self.name, self.email, self.phone)


# acest bloc se executa doar cand se apeleaza acest script
# se recomanda folosirea acestui bloc pentru a nu se executa la import
if __name__ == '__main__':
    agenda = Agenda()
    agenda.add_contact(Contact('Ion Popescu', 'ion_popescu@gmail.com'))
    agenda.add_contact(Friend('Alex Andre', 'ion_popescu@gmail.com', 756277066))
    agenda.add_contact(Friend('Marius Marius', 'ion_popescu@gmail.com', 343431443))
    agenda.add_contact(Contact('Andy Di', 'ion_popescu@gmail.com'))
    agenda.print_agenda()
