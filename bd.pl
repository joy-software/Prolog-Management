
:- thread_local thread_message_hook/3.
:- dynamic thread_message_hook/3.
:- volatile thread_message_hook/3.


:- dynamic appareil/4.

appareil(1, 2, iPhone5, 400).
appareil(5, 2, 'S4', 400).
appareil(3, 1, 'HpG8', 1000).
appareil(8, 3, 'Cisco360', 1200).
appareil(4, 3, 'Fortigate4', 900).
appareil(11, 1, 'HpG6', 800).

:- dynamic detailFacture/2.

detailFacture(2, 5).
detailFacture(7, 8).
detailFacture(2, 1).
detailFacture(4, 1).
detailFacture(2, 3).
detailFacture(7, 11).
detailFacture(7, 3).

:- dynamic facture/2.

facture(2, 1900).
facture(7, 3000).
facture(4, 10000).

:- dynamic type/2.

type(1, 'Desktop').
type(2, 'Phone').
type(3, 'Routeur').
type(10, fbfgb).
