struct channel;

/* flags */
#define CHANNEL_PROCESS_ONECPY 2
#define CHANNEL_PROCESS_SHARED 1


struct channel *channel_create(int eltsize, int size, int flags);
void channel_destroy(struct channel *channel);
int channel_send(struct channel *channel, const void *data);
int channel_close(struct channel *channel);
int channel_recv(struct channel *channel, void *data);

struct channel *
channel_unrelated_create (int eltsize, int size, char *path);

struct channel *
channel_unrelated_open(int len, char *path);


